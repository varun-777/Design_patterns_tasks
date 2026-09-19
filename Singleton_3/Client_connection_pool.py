from singleton_connection_pool import ConnectionPoolImpl


def main():

    # TODO 1:
    # Get a Connection Pool with a maximum of 5 connections.
    pool1 = None
    pool1 = ConnectionPoolImpl.get_instance(5)

    # TODO 2:
    # Initialize the pool.

    pool1.initialize_pool()

    # TODO 3:
    # Check the initial counts.
    #
    print("Total connections:", pool1.get_total_connections_count())
    print("Available connections:", pool1.get_available_connections_count())

    # TODO 4:
    # Get connection.
    connection1 = pool1.get_connection()
    connection2 = pool1.get_connection()
    connection3 = pool1.get_connection()
    connection4 = pool1.get_connection()
    connection5 = pool1.get_connection()

    # TODO 5:
    # Check available connections after acquiring one.
    print("Available connections after get:",
          pool1.get_available_connections_count())

    # TODO 6:
    # Release the connection.
    pool1.release_connection(connection2)
    pool1.release_connection(connection3)
    pool1.release_connection(connection4)
    pool1.release_connection(connection5)

    # TODO 7:
    # Check available connections after releasing it.
    print("Available connections after release:",
          pool1.get_available_connections_count())

    # TODO 8:
    # Get the Singleton again.
    pool2 = None
    pool2 = ConnectionPoolImpl.get_instance(10)

    # TODO 9:
    # Verify that pool1 and pool2 are the same object.
    print("Same instance:", pool1 is pool2)

    # TODO 10:
    # Reset the Singleton.
    ConnectionPoolImpl.reset_instance()

    # TODO 11:
    # Get a new pool.
    pool3 = None
    pool3 = ConnectionPoolImpl.get_instance(10)

    # TODO 12:
    # Verify that a new object was created.
    print("New instance after reset:", pool1 is pool3)


if __name__ == "__main__":
    main()
