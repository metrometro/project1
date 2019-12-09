package com.epam.finalproject.comand;

import com.epam.finalproject.comand.pagebutton.*;
import com.epam.finalproject.comand.impl.*;
import com.epam.finalproject.comand.trainer.*;
import com.epam.finalproject.comand.user.*;

public enum CommandType {
    MARK_USERS_VISIT {
        {
            this.command = new MarkUsersVisitCommand();
        }
    },
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    REGISTRATION_BUTTON {
        {
            this.command = new RegistrationPathCommand();
        }
    },
    LOGIN_BUTTON {
        {
            this.command = new LoginPathCommand();
        }
    },
    RESULT_BUTTON {
        {
            this.command = new ResultCommand();
        }
    },
    USER_MAIN_BUTTON {
        {
            this.command = new UserMainCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    SHOW_ALL_EXERCISES {
        {
            this.command = new ShowAllExercisesCommand();
        }
    },
    SHOW_ALL_USERS {
        {
            this.command = new ShowAllUsersCommand();
        }
    },
    SHOW_ALL_PAID_USERS_WITH_PERSONAL_TRAINER {
        {
            this.command = new ShowAllPaidUsersWithPersonalTrainerCommand();
        }
    },
    BECOME_PERSONAL_TRAINER {
        {
            this.command = new BecomePersonalTrainerCommand();
        }
    },
    MARK_PERSONAL_USERS_VISIT {
        {
            this.command = new MarkPersonalUsersVisitCommand();
        }
    },
    SHOW_USER_DIET {
        {
            this.command = new ShowUserDietCommand();
        }
    },
    SHOW_ALL_NOT_ACTIVE_USERS {
        {
            this.command = new ShowAllNotActiveUsers();
        }
    },
    SHOW_ALL_TRAINERS {
        {
            this.command = new ShowAllTrainersCommand();
        }
    },
    SHOW_ALL_PAID_USERS {
        {
            this.command = new ShowAllPaidUsersCommand();
        }
    },
    SHOW_ALL_USERS_WITHOUT_EXERCISES {
        {
            this.command = new ShowAllUsersWithoutExercisesCommand();
        }
    },
    SHOW_ALL_USERS_WITHOUT_DIET {
        {
            this.command = new ShowAllUsersWithoutDietCommand();
        }
    },
    DELETE_DIETS{
        {
            this.command = new DeleteDietsCommand();
        }
    },
    DELETE_EXERCISES {
        {
            this.command = new DeleteExercisesCommand();
        }
    },
    DELETE_USER {
        {
            this.command = new DeleteUserCommand();
        }
    },
    DELETE_CURRENT_USER_DIET {
        {
            this.command = new DeleteCurrentUserDietCommand();
        }
    },
    DELETE_CHOSEN_PERSONAL_EXERCISES {
        {
            this.command = new DeleteChosenPersonalExercisesCommand();
        }
    },
    DELETE_CHOSEN_PERSONAL_DIET {
        {
            this.command = new DeleteChosenPersonalDietCommand();
        }
    },
    DELETE_CURRENT_USER_EXERCISES {
        {
            this.command = new DeleteCurrentUserExercisesCommand();
        }
    },
    PAYMENT_FORM {
        {
            this.command = new PaymentFormCommand();
        }
    },
    WRITE_COMMENT {
        {
            this.command = new WriteCommentCommand();
        }
    },
    CREATE_NEW_DIET{
        {
            this.command = new CreateNewDietCommand();
        }
    },
    CREATE_NEW_EXERCISE {
        {
            this.command = new CreateNewExercisesCommand();
        }
    },
    CREATE_COMMENT {
        {
            this.command = new CreateCommentCommand();
        }
    },
    CHOOSE_CURRENT_USER_DIET {
        {
            this.command = new ChooseCurrentUserDietCommand();
        }
    },
    CHOOSE_CURRENT_USER_EXERCISES {
        {
            this.command = new ChooseCurrentUserExercisesCommand();
        }
    },
    CHOOSE_PAYMENT {
        {
            this.command = new ChoosePaymentCommand();
        }
    },
    CHOOSE_PERSONAL_EXERCISES {
        {
            this.command = new ChoosePersonalExercisesCommand();
        }
    },
    CHOOSE_PERSONAL_DIET {
        {
            this.command = new ChoosePersonalDietCommand();
        }
    },
    CHOOSE_PERSONAL_USERS_MARK {
        {
            this.command = new ChoosePersonalUsersMarkCommand();
        }
    },
    SET_CURRENT_USER_DIET {
        {
            this.command = new SetCurrentUserDietCommand();
        }
    },
    SET_CURRENT_USER_EXERCISES {
        {
            this.command = new SetCurrentUserExercisesCommand();
        }
    },
    SET_EXERCISES_FOR_USER {
        {
            this.command = new SetExercisesForUserCommand();
        }
    },
    SET_PERSONAL_EXERCISES {
        {
            this.command = new SetPersonalExercisesCommand();
        }
    },
    SET_PERSONAL_DIET {
        {
            this.command = new SetPersonalDietCommand();
        }
    },
    SET_DIET_FOR_USER {
        {
            this.command = new SetDietForUserCommand();
        }
    },
    CHOOSE_EXERCISES {
        {
            this.command = new ChooseExercisesCommand();
        }
    },
    CHOOSE_DIET {
        {
            this.command = new ChooseDietCommand();
        }
    },
    RESTORE_USER {
        {
            this.command = new RestoreUserCommand();
        }
    },
    MAKE_TRAINER {
        {
            this.command = new MakeTrainerCommand();
        }
    },
    MAKE_USER {
        {
            this.command = new MakeUserCommand();
        }
    },
    SHOW_ALL_DIETS{
        {
            this.command = new ShowAllDietsCommand();
        }
    },
    SHOW_CURRENT_USER_STATE {
        {
            this.command = new ShowCurrentUserStateCommand();
        }
    },
    SHOW_CURRENT_USER_COMMENTS {
        {
            this.command = new ShowCurrentUserCommentsCommand();
        }
    },
    SHOW_EXERCISES_FOR_CURRENT_USER {
        {
            this.command = new ShowExercisesForCurrentUserCommand();
        }
    },
    SHOW_DIET_FOR_CURRENT_USER {
        {
            this.command = new ShowDietForCurrentUserCommand();
        }
    },
    SHOW_USER_EXERCISES {
        {
            this.command = new ShowUserExercisesCommand();
        }
    },
    SHOW_ALL_TRAINERS_USERS {
        {
            this.command = new ShowAllTrainersUsersCommand();
        }
    },
    PAY {
        {
            this.command = new PayCommand();
        }
    };

    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
