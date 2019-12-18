package com.epam.finalproject.comand;

import com.epam.finalproject.comand.impl.*;
import com.epam.finalproject.comand.pagebutton.LoginCommand;
import com.epam.finalproject.comand.pagebutton.RegistrationCommand;
import com.epam.finalproject.comand.pagebutton.ResultCommand;
import com.epam.finalproject.comand.pagebutton.UserMainCommand;

public enum CommandType {
    MARK_USERS_VISIT {
        {
            this.command = new TrainerMarkUsersVisitCommand();
        }
    },
    LOGIN {
        {
            this.command = new com.epam.finalproject.comand.impl.LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    REGISTRATION_BUTTON {
        {
            this.command = new RegistrationCommand();
        }
    },
    LOGIN_BUTTON {
        {
            this.command = new LoginCommand();
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
            this.command = new com.epam.finalproject.comand.impl.RegistrationCommand();
        }
    },
    SHOW_ALL_EXERCISES {
        {
            this.command = new AdminShowAllExercisesCommand();
        }
    },
    SHOW_ALL_USERS {
        {
            this.command = new AdminShowAllUsersCommand();
        }
    },
    SHOW_ALL_PAID_USERS_WITH_PERSONAL_TRAINER {
        {
            this.command = new TrainerShowAllPaidUsersWithPersonalTrainerCommand();
        }
    },
    BECOME_PERSONAL_TRAINER {
        {
            this.command = new TrainerBecomePersonalTrainerCommand();
        }
    },
    MARK_PERSONAL_USERS_VISIT {
        {
            this.command = new TrainerMarkPersonalUsersVisitCommand();
        }
    },
    SHOW_USER_DIET {
        {
            this.command = new TrainerShowUserDietCommand();
        }
    },
    SHOW_ALL_NOT_ACTIVE_USERS {
        {
            this.command = new AdminShowAllNotActiveUsers();
        }
    },
    SHOW_ALL_TRAINERS {
        {
            this.command = new AdminShowAllTrainersCommand();
        }
    },
    SHOW_ALL_PAID_USERS {
        {
            this.command = new TrainerShowAllPaidUsersCommand();
        }
    },
    SHOW_ALL_USERS_WITHOUT_EXERCISES {
        {
            this.command = new TrainerShowAllUsersWithoutExercisesCommand();
        }
    },
    SHOW_ALL_USERS_WITHOUT_DIET {
        {
            this.command = new TrainerShowAllUsersWithoutDietCommand();
        }
    },
    DELETE_DIETS{
        {
            this.command = new AdminDeleteDietsCommand();
        }
    },
    DELETE_EXERCISES {
        {
            this.command = new AdminDeleteExercisesCommand();
        }
    },
    DELETE_USER {
        {
            this.command = new AdminDeleteUserCommand();
        }
    },
    DELETE_CURRENT_USER_DIET {
        {
            this.command = new UserDeleteCurrentUserDietCommand();
        }
    },
    DELETE_CHOSEN_PERSONAL_EXERCISES {
        {
            this.command = new TrainerDeleteChosenPersonalExercisesCommand();
        }
    },
    DELETE_CHOSEN_PERSONAL_DIET {
        {
            this.command = new TrainerDeleteChosenPersonalDietCommand();
        }
    },
    DELETE_CURRENT_USER_EXERCISES {
        {
            this.command = new UserDeleteCurrentUserExercisesCommand();
        }
    },
    PAYMENT_FORM {
        {
            this.command = new UserPaymentFormCommand();
        }
    },
    WRITE_COMMENT {
        {
            this.command = new UserWriteReviewCommand();
        }
    },
    CREATE_NEW_DIET{
        {
            this.command = new AdminCreateNewDietCommand();
        }
    },
    CREATE_NEW_EXERCISE {
        {
            this.command = new AdminCreateNewExercisesCommand();
        }
    },
    CREATE_COMMENT {
        {
            this.command = new UserCreateReviewCommand();
        }
    },
    CHOOSE_CURRENT_USER_DIET {
        {
            this.command = new UserChooseCurrentUserDietCommand();
        }
    },
    CHOOSE_CURRENT_USER_EXERCISES {
        {
            this.command = new UserChooseCurrentUserExercisesCommand();
        }
    },
    CHOOSE_PAYMENT {
        {
            this.command = new UserChoosePaymentCommand();
        }
    },
    CHOOSE_PERSONAL_EXERCISES {
        {
            this.command = new TrainerChoosePersonalExercisesCommand();
        }
    },
    CHOOSE_PERSONAL_DIET {
        {
            this.command = new TrainerChoosePersonalDietCommand();
        }
    },
    CHOOSE_PERSONAL_USERS_MARK {
        {
            this.command = new TrainerChoosePersonalUsersMarkCommand();
        }
    },
    SET_CURRENT_USER_DIET {
        {
            this.command = new UserSetCurrentUserDietCommand();
        }
    },
    SET_CURRENT_USER_EXERCISES {
        {
            this.command = new UserSetCurrentUserExercisesCommand();
        }
    },
    SET_EXERCISES_FOR_USER {
        {
            this.command = new TrainerSetExercisesForUserCommand();
        }
    },
    SET_PERSONAL_EXERCISES {
        {
            this.command = new TrainerSetPersonalExercisesCommand();
        }
    },
    SET_PERSONAL_DIET {
        {
            this.command = new TrainerSetPersonalDietCommand();
        }
    },
    SET_DIET_FOR_USER {
        {
            this.command = new TrainerSetDietForUserCommand();
        }
    },
    CHOOSE_EXERCISES {
        {
            this.command = new TrainerChooseExercisesCommand();
        }
    },
    CHOOSE_DIET {
        {
            this.command = new TrainerChooseDietCommand();
        }
    },
    RESTORE_USER {
        {
            this.command = new AdminRestoreUserCommand();
        }
    },
    MAKE_TRAINER {
        {
            this.command = new AdminMakeTrainerCommand();
        }
    },
    MAKE_USER {
        {
            this.command = new AdminMakeUserCommand();
        }
    },
    SHOW_ALL_DIETS{
        {
            this.command = new AdminShowAllDietsCommand();
        }
    },
    SHOW_CURRENT_USER_STATE {
        {
            this.command = new UserShowCurrentUserStateCommand();
        }
    },
    SHOW_CURRENT_USER_COMMENTS {
        {
            this.command = new UserShowCurrentUserReviewsCommand();
        }
    },
    SHOW_EXERCISES_FOR_CURRENT_USER {
        {
            this.command = new UserShowExercisesForCurrentUserCommand();
        }
    },
    SHOW_DIET_FOR_CURRENT_USER {
        {
            this.command = new UserShowDietForCurrentUserCommand();
        }
    },
    SHOW_USER_EXERCISES {
        {
            this.command = new TrainerShowUserExercisesCommand();
        }
    },
    SHOW_ALL_TRAINERS_USERS {
        {
            this.command = new TrainerShowAllTrainersUsersCommand();
        }
    },
    PAY {
        {
            this.command = new UserPayCommand();
        }
    };

    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}