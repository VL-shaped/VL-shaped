team = input('Please enter the name of the football team: ')
no_of_games = int(input('Please enter the number of games: '))
results_list = []

for count in range(no_of_games):

    while True:
        try:
            scored_goals = int(input('Scored goals: '))
        except ValueError:
            print('This is an invalid data type, please enter a whole number')
        else:
            try:
                against_goals = int(input('Against goals: '))
            except ValueError:
                print('This is an invalid data type, please enter a whole number')
            else:
                results_list.append((scored_goals, against_goals))
                print('Results Accepted')
                break

wins = sum(result[0] > result[1] for result in results_list)
draws = sum(result[0] == result[1] for result in results_list)
losses = sum(result[0] < result[1] for result in results_list)
league_points = ((wins * 3) + draws)

print('Team Name: ' + team)
print('Wins: ' + str(wins))
print('Losses: ' + str(losses))
print('Draws: ' + str(draws))
print('League points: ' + str(league_points))
