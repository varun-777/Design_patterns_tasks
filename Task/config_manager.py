from abc import ABC, abstractmethod


class Config_Manager(ABC):
    """Abstract base class for configuration managers."""

    def __init__(self, properties=None):
        """Initialize configuration properties dictionary."""
        self.properties = properties if properties is not None else {}

    @abstractmethod
    def get_configuration(self, key, value_type=None):
        """Retrieve a configuration value by key, optionally converted to value_type."""
        pass

    @abstractmethod
    def set_configuration(self, key, value):
        """Store a configuration key-value pair."""
        pass

    @abstractmethod
    def remove_configuration(self, key):
        """Remove a configuration setting by key."""
        pass

    @abstractmethod
    def clear(self):
        """Clear all configuration settings."""
        pass

