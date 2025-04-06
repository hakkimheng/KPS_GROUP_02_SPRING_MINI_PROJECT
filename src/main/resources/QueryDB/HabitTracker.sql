-- Enable the UUID extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create table
CREATE TABLE app_users (
                           app_user_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                           username VARCHAR(255) NOT NULL,
                           email VARCHAR(255) UNIQUE NOT NULL,
                           password VARCHAR(255) NOT NULL,
                           level INT DEFAULT 1,
                           xp INT DEFAULT 0,
                           profile_image TEXT,
                           is_verified BOOLEAN DEFAULT FALSE,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE achievements (
                              achievement_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                              title VARCHAR(255) NOT NULL,
                              description TEXT,
                              badge TEXT,
                              xp_required INT NOT NULL
);

CREATE TABLE app_user_achievements (
                                       app_user_achievement_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                       app_user_id UUID NOT NULL,
                                       achievement_id UUID NOT NULL,
                                       FOREIGN KEY (app_user_id) REFERENCES app_users(app_user_id) ON DELETE CASCADE,
                                       FOREIGN KEY (achievement_id) REFERENCES achievements(achievement_id) ON DELETE CASCADE
);

CREATE TABLE habits (
                        habit_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                        title VARCHAR(255) NOT NULL,
                        description TEXT,
                        frequency VARCHAR(50),
                        is_active BOOLEAN DEFAULT TRUE,
                        app_user_id UUID NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (app_user_id) REFERENCES app_users(app_user_id) ON DELETE CASCADE
);

CREATE TABLE habit_logs (
                            habit_log_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                            log_date DATE NOT NULL,
                            status VARCHAR(50) NOT NULL,
                            xp_earned INT DEFAULT 0,
                            habit_id UUID NOT NULL,
                            FOREIGN KEY (habit_id) REFERENCES habits(habit_id) ON DELETE CASCADE
);


select * from habits where habit_id = 'bd40a3c4-4054-44eb-aa98-f4fb100574d2'


SELECT * FROM achievements
WHERE xp_required <= 1000
  AND achievement_id NOT IN (
    SELECT achievement_id FROM app_user_achievements
    WHERE app_user_id = '3fe9b4b6-012c-4a65-a9d9-5938c6fc8c5c'
    );

SELECT * FROM app_users;

SELECT * FROM achievements a INNER JOIN app_user_achievements aua
                                        ON a.achievement_id = aua.achievement_id
         INNER JOIN app_users au ON aua.app_user_id = au.app_user_id
WHERE a.xp_required <= 1000 AND aua.app_user_id = '3fe9b4b6-012c-4a65-a9d9-5938c6fc8c5c'
-- LIMIT #{size} OFFSET #{size} * (#{page} - 1)

SELECT * FROM app_user_achievements WHERE app_user_id = '3fe9b4b6-012c-4a65-a9d9-5938c6fc8c5c';
