CREATE DATABASE IF NOT EXISTS bmi_localization CHARACTER
SET
    utf8mb4 COLLATE utf8mb4_unicode_ci;

USE bmi_localization;

-- Stores BMI calculation results
CREATE TABLE
    IF NOT EXISTS bmi_results (
        id INT AUTO_INCREMENT PRIMARY KEY,
        weight DOUBLE NOT NULL,
        height DOUBLE NOT NULL,
        bmi DOUBLE NOT NULL,
        language VARCHAR(10),
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Stores localized text for UI
CREATE TABLE
    IF NOT EXISTS localization_strings (
        id INT AUTO_INCREMENT PRIMARY KEY,
        `key` VARCHAR(100) NOT NULL,
        value VARCHAR(255) NOT NULL,
        language VARCHAR(10) NOT NULL
    );

-- Sample localization entries (English)
INSERT INTO
    localization_strings (`key`, value, language)
VALUES
    ('weight', 'Weight (kg)', 'en'),
    ('height', 'Height (cm)', 'en'),
    ('calculate', 'Calculate BMI', 'en'),
    ('result', 'Your BMI is', 'en'),
    ('invalid', 'Invalid input', 'en'),
    ('localTime', 'Local Time', 'en');

-- Sample localization entries (French)
INSERT INTO
    localization_strings (`key`, value, language)
VALUES
    ('weight', 'Poids (kg)', 'fr'),
    ('height', 'Taille (cm)', 'fr'),
    ('calculate', 'Calculer IMC', 'fr'),
    ('result', 'Votre IMC est', 'fr'),
    ('invalid', 'Entrée invalide', 'fr'),
    ('localTime', 'Heure locale', 'fr');

-- Sample localization entries (Vietnamese)
INSERT INTO
    localization_strings (`key`, value, language)
VALUES
    ('weight', 'Cân nặng (kg)', 'vn'),
    ('height', 'Chiều cao (cm)', 'vn'),
    ('calculate', 'Tính BMI', 'vn'),
    ('result', 'Chỉ số BMI của bạn là', 'vn'),
    ('invalid', 'Dữ liệu không hợp lệ', 'vn'),
    ('localTime', 'Giờ địa phương', 'vn');

-- Sample localization entries (Urdu)
INSERT INTO
    localization_strings (`key`, value, language)
VALUES
    ('weight', ' وزن)کلوگرام( ', 'ur'),
    ('height', ' قد)سینٹیمیٹر( ', 'ur'),
    ('calculate', 'BMI حسابکریں ', 'ur'),
    ('result', ' آپکا BMI ہے', 'ur'),
    ('invalid', ' غلطانپٹ ', 'ur'),
    ('localTime', ' مقامیوقت ', 'ur');