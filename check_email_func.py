# the function below checks if user input is a valid email address

email_list = ()


def email_checker(full_email):
    for i in email_list:
        while True:
            if full_email.count('@') != 1:
                print('Email address must contain one @ symbol, please try again.')
            else:

                if email_add == '':
                    print('Email address must contain some letters before the @ symbol, please try again.')
                else:
                    if domain.count('.') == 0:
                        print('Email address must contain a full stop in the domain, please try again')
                    else:
                        print(str(full_email) + ' is a valid email address at the domain ' + str(domain))
    return


email_checker((input(str("Please enter an email address: "))))

# string must contain only 1 at symbol ie count them
# must be some characters before at symbol, ie not empty
# chars after at symbol must be the domain only ie xxx.xxx
