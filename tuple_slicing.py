students = ("vicky", "becks", "steph", "ruby")

print(len(students))  # number of elements in the students tuple is 4
print(students[0])  # index 0, the first element
print(students[-1])  # the last element will be ruby
print(students[:-1])  # displays everything but the last element
print(students[3])  # index number three will be ruby
print(students[2])  # the third element i.e. 0, 1, 2, will be steph

new_students = ("ripley",)  # add ripley to tuple
all_students = students + new_students
print(all_students)  # print all tuple elements

print("vicky" in students)  # true or false that 'vicky' is in the tuple

print(students.index("steph"))  # what index number is steph in the students tuple

print("This is an apostrophe... student\'s")  # this is an apostrophe
