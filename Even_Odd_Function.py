import random


def odd_or_even(number_entered):
    if not isinstance(number_entered, int):
        raise TypeError('Invalid Data Type, enter a whole number')

    return number_entered % 2 == 0  # determines whether a number is even or odd, returning a true or false result


rand_list = random.sample(range(0, 101), 10)
for i in rand_list:
    answer = odd_or_even(i)
    if answer == 0:
        print(str(i) + ' is odd')
    else:
        print(str(i) + ' is even')
