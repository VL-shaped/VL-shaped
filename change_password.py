print('Hello, lets create a new password')
username = input('Please enter your Username: ')
student_id_no = input('Please enter your Student ID number: ')
banned_passwords = [username, student_id_no, "huddersfield", "password", "cheese", "programming"]

while True:
    pass_1 = input('Please enter your new password: ')
    if len(pass_1) < 6:
        print('Invalid input. Please ensure your password is 6-12 characters long')
    elif len(pass_1) > 12:
        print('Invalid input. Please ensure your password is 6-12 characters long')
    elif pass_1 in banned_passwords:
        print('Sorry, but that password is not allowed')
    else:
        print('Thank you')
        break

while True:
    pass_2 = input('Please re-enter your password so we can check it against your first valid input: ')
    if len(pass_2) < 6:
        print('Invalid input. Please ensure your password is 6-12 characters long')
    elif len(pass_2) > 12:
        print('Invalid input. Please ensure your password is 6-12 characters long')
    else:
        print('Thank you')
        break

if pass_1 == pass_2:
    print('Your password has been updated')
else:
    print('Sorry, those passwords do not match')

