students = ("vicky", "becks", "Steph", "Ruby")

print(len(students))  # number of elements
print(students[0])  # index 0
print(students[-1])  # the last element
print(students[:-1])  # everything but the last element

new_students = ("Ripley",)  # add to tuple
all_students = students + new_students
print(all_students)

print("vicky" in students)  # true or false
print(students.index("Steph"))  # what index number is steph in the students tuple

if "vicky" in students:
    print("This student exists in the tuple")
else:
    print("This student does NOT exist in the tuple")  # need exception rule here

Brand_New_Students = input("Please enter the students first name: ").capitalize()

if Brand_New_Students in all_students:
    print("A student with that first name already exists")
else:
    print("That name does not currently exist")

input("Enter the student\'s name")  # this is an apostrophe

