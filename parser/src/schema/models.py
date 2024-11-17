from pydantic.v1 import BaseModel


class Student(BaseModel):
    full_name: str
    group: str
    course: int


class Discipline(BaseModel):
    name: str
    terms: list[int]
