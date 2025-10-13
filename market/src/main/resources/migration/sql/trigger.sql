/**/
use charly_market;

DELIMITER //

CREATE TRIGGER trg_user_update_log
    AFTER UPDATE ON users
    FOR EACH ROW
BEGIN
    -- 닉네임 변경
    IF NEW.nickname <> OLD.nickname THEN
        INSERT INTO user_log (user_id, log_content, column_name)
        VALUES (OLD.id,
                CONCAT('닉네임 변경: ', OLD.nickname, ' → ', NEW.nickname),
                'nickname');
END IF;

-- 전화번호 변경
IF NEW.phone <> OLD.phone THEN
        INSERT INTO user_log (user_id, log_content, column_name)
        VALUES (OLD.id,
                CONCAT('전화번호 변경: ', OLD.phone, ' → ', NEW.phone),
                'phone');
END IF;

    -- 비밀번호 변경
    IF NEW.password <> OLD.password THEN
        INSERT INTO user_log (user_id, log_content, column_name)
        VALUES (OLD.id,
                '비밀번호 변경 처리',
                'password');
END IF;

    -- 이메일 변경
    IF NEW.email <> OLD.email THEN
        INSERT INTO user_log (user_id, log_content, column_name)
        VALUES (OLD.id,
                CONCAT('이메일 변경: ', OLD.email, ' → ', NEW.email),
                'email');
END IF;

    -- 상태 변경
    IF NEW.status <> OLD.status THEN
        INSERT INTO user_log (user_id, log_content, column_name)
        VALUES (OLD.id,
                CONCAT('상태 변경: ', OLD.status, ' → ', NEW.status),
                'status');
END IF;
END //

DELIMITER ;