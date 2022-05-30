print('Hello and Welcome to Vicky\'s Sweet Shop')

shopping_list = []
number_of_sweets = int(input('How many sweets do you have? '))
for count in range(number_of_sweets):

    while True:
        try:
            sweets = str(input('Please type the cost of sweet ' + str(count + 1) + ': '))
            sweet = int(sweets[:-1])  # remove the 'p' and convert to integer
        except ValueError:
            print('This is an invalid data type, please enter a whole number, in pence')
        else:
            shopping_list.append(sweet)
            print('Value Accepted')
            break

print('The total cost of these sweets is: ' + str(sum(shopping_list)) + str('p'))  # total
print('The lowest priced sweet is: ' + str(min(shopping_list)) + str('p'))  # lowest value
print('The highest priced sweet is: ' + str(max(shopping_list)) + str('p'))  # highest value
print('The average price is: ' + str(sum(shopping_list) // len(shopping_list)) + str('p'))  # average value
