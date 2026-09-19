
from logger import Logger, LogLevel
from singleton_logger import LoggerImpl


class Payment:

    def __init__(self):
        self.logger = LoggerImpl.get_instance()

        self.logger.set_log_file("application.log")

    def pay(self):
        self.logger.log(LogLevel.error, "Payment is failed")

        print("Payment is sucess")

    def refund(self):
        pass

    def PrintId(self, other):
        print(id(self.logger))
        print(id(other))
