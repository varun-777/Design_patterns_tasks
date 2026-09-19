from connection_pool import ConnectionPool, DatabaseConnection
import threading


class ConnectionPoolImpl(ConnectionPool):

    _instance = None
    _instance_lock = threading.Lock()
    _pool_lock = threading.Lock()
    initialization = False

    def __new__(cls, max_connections):
        if cls._instance is None:
            with cls._instance_lock:
                if cls._instance is None:
                    cls._instance = super().__new__(cls)
        return cls._instance

    def __init__(self, max_connections):
        if hasattr(self, "_initialized"):
            return

        self.available_connections = max_connections
        self.max_connections = max_connections
        self.inuse = []
        self.connections = []
        self._initialized = True
        for i in range(self.max_connections):
            self.connections.append(DatabaseConnection(i))

    @classmethod
    def get_instance(cls, max_connections):
        if cls._instance is None:
            cls._instance = cls(max_connections)
        return cls._instance

    @classmethod
    def reset_instance(cls):
        cls._instance = None

    def initialize_pool(self):
        if not self.initialization:
            for i in range(self.max_connections):
                self.inuse.append(False)
            self.initialization = True

    def get_connection(self):
        # TODO:
        with self._pool_lock:
            for i in range(self.max_connections):
                if not self.inuse[i]:
                    self.inuse[i] = True
                    self.available_connections -= 1
                    return self.connections[i]
            if self.available_connections <= 0:
                print(
                    "Max connections exceeded in the current pool,you can't get a connection now")

    def release_connection(self, connection):
        with self._pool_lock:
            for i in range(self.max_connections):
                if self.connections[i] is connection:
                    if self.inuse[i] == False:
                        print("This connection is already released!!")
                    else:
                        self.inuse[i] = False
                        self.available_connections += 1

    def get_available_connections_count(self):
        # TODO:
        return self.available_connections

    def get_total_connections_count(self):
        # TODO:
        return self.max_connections
