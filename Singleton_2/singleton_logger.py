from datetime import datetime
import threading

from logger import Logger, LogLevel


class LoggerImpl(Logger):

    _instance = None
    _instance_lock = threading.Lock()

    def __new__(cls):
        # TODO:
        if (cls._instance is None):
            with cls._instance_lock:
                if (cls._instance is None):
                    cls._instance = super().__new__(cls)
        return cls._instance

    def __init__(self):
        # TODO:
        if (hasattr(self, "_initilized")):
            return
        self.file = None
        self._initilized = True

    @classmethod
    def get_instance(cls):
        # TODO:
        if (cls._instance is None):
            cls._instance = cls()
        return cls._instance

    @classmethod
    def reset_instance(cls):
        # TODO:
        cls._instance = None

    def set_log_file(self, file_path):
        # TODO:
        self.file = open(file_path, "a")

    def log(self, level, message):
        # TODO:
        d = datetime.now()
        entry = f"{d} [{level.value}] {message}\n"
        self.file.write(entry)

    def get_log_file(self):
        # TODO:
        return self.file

    def flush(self):
        # TODO:
        # Flush buffered log entries.
        self.file.flush()
        pass

    def close(self):
        # TODO:
        # Close the file resource.

        self.file.close()
