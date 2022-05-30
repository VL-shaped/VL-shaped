name = input('Hello there, What is your name? ').capitalize()

if name == "Arthur":  # THE INPUT CAN BE ANY MIX OF LOWER AND UPPERCASE but the value that must equal true, must be the
    # same format as the inputted value
    print("Hello My King, And Good Day To You King " + name + '!')
elif 'Sir' in name:
    print('Hello and nice to meet you ' + name)  # if the user inputted value is 'sir' someone, remove the word sir from
    # the outputted result
else:
    print('Hello and nice to meet you Sir ' + name)
    print('I hope you have a nice day Sir ' + name)



