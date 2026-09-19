from config_manager import Config_Manager


class FileBasedConfigurationManager(Config_Manager):
    # Store the Singleton instance here.
    _instance = None

    def __new__(cls):
        # Control object creation so that only one
        # FileBasedConfigurationManager object exists.
        if cls._instance is None:
            cls._instance = super().__new__(cls)
        return cls._instance

    def __init__(self, properties=None):
        # Initialize the parent class.
        # Be careful: __init__ can run more than once
        # when using a Singleton with __new__.
        if not getattr(self, "_initialized", False):
            super().__init__(properties)
            self._initialized = True

    @classmethod
    def get_instance(cls):
        # Return the Singleton instance.
        if cls._instance is None:
            cls._instance = cls()
        return cls._instance

    @classmethod
    def reset_instance(cls):
        # Reset the Singleton instance.
        cls._instance = None

    def get_configuration(self, key, value_type=None):
        # 1. Get the value using key.
        # 2. If it does not exist, return None.
        if key not in self.properties:
            return None

        value = self.properties[key]

        # 3. If value_type is None, return the value.
        if value_type is None or value is None:
            return value

        # 4. Otherwise convert it to the requested type.
        if isinstance(value_type, str):
            type_mapping = {
                "int": int,
                "float": float,
                "str": str
            }
            value_type = type_mapping.get(value_type.lower(), value_type)

            return value_type(value)

        return value

    def set_configuration(self, key, value):
        # Store the configuration.
        self.properties[key] = value

    def remove_configuration(self, key):
        # Remove the configuration if it exists.
        if key in self.properties:
            del self.properties[key]

    def clear(self):
        # Remove all configurations.
        self.properties.clear()
