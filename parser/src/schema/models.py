from pydantic.v1 import BaseModel


class Student(BaseModel):
    name: str
    surname: str
    patronymic: str


class Discipline(BaseModel):
    name: str
    terms: list[int]
