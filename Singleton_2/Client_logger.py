from singleton_logger import LoggerImpl

from logger import LogLevel

from Payment import Payment


def main():

    # TODO 1:
    # Get the first Logger instance.
    logger1 = LoggerImpl()

    # TODO 2:
    # Configure the log file.
    logger1.set_log_file("application.log")

    # TODO 3:
    # Write messages with different log levels.
    #
    logger1.log(LogLevel.info, "Application started")
    logger1.log(LogLevel.debug, "User service initialized")
    logger1.log(LogLevel.warn, "Payment service is slow")
    logger1.log(LogLevel.error, "Payment failed")

    p1 = Payment()
    p1.PrintId(logger1)

    # TODO 4:
    # Get the log file path.
    print("Log file:", logger1.get_log_file())

    # TODO 5:
    # Flush the logger.
    #
    logger1.flush()

    # TODO 6:
    # Get the Singleton instance again.
    logger2 = None

    # TODO 7:
    # Verify both references point to the same object.
    print("Same instance:", logger1 is logger2)

    # TODO 8:
    # Close the logger.
    #
    logger1.close()

    # TODO 9:
    # Reset the Singleton.
    #
    # LoggerImpl.reset_instance()

    # TODO 10:
    # Get the Logger instance again.
    logger3 = None

    # TODO 11:
    # Verify that a new object was created.
    #
    print("New instance after reset:", logger1 is logger3)


if __name__ == "__main__":
    main()
