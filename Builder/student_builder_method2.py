class Student:
    _key = object()

    def __init__(self, builder, _key=None):
        if _key is not Student._key:
            raise TypeError("Use Student.get_builder()....build()")
        self.name = builder.name
        self.age = builder.age
        self.grad_year = builder.grad_year
        print("Student object is successfully created")

    class Builder:
        def __init__(self):
            self._name = ""
            self._age = 0
            self._grad_year = 0

        def set_name(self, n: str) -> "Student.Builder":
            self._name = n
            return self

        def set_age(self, a: int) -> "Student.Builder":
            self._age = a
            return self

        def set_grad_year(self, y: int) -> "Student.Builder":
            self._grad_year = y
            return self

        @property
        def name(self) -> str: return self._name

        @property
        def age(self) -> int: return self._age

        @property
        def grad_year(self) -> int: return self._grad_year

        def build(self) -> "Student":
            if self._grad_year > 2022:
                raise ValueError("Grad year cannot be greater than 2022")

            return Student(self, Student._key)

    @staticmethod
    def get_builder() -> "Student.Builder":
        return Student.Builder()


s = Student.get_builder().set_name(
    "Varun Kumar").set_age(20).set_grad_year(2022).build()
