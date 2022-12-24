insert into public.address (city, deleted, state, street, zip_code) values ('Fairfield', false, 'Iowa', '1000N 4TH ST BLD 140', '52557');
insert into public.address (city, deleted, state, street, zip_code) values ('Fairfield', false, 'Iowa', '1000N 4TH ST BLD 141', '52557');
insert into public.address (city, deleted, state, street, zip_code) values ('Fairfield', false, 'Iowa', '1000N 4TH ST BLD 142', '52557');
insert into public.address (city, deleted, state, street, zip_code) values ('Sarasota', false, 'Florida', 'SOMEWHERE IN FL', '33133');
insert into public.address (city, deleted, state, street, zip_code) values ('Sarasota', false, 'Florida', 'SOMEWHERE IN XXX', '33133');
insert into public.address (city, deleted, state, street, zip_code) values ('Sarasota', false, 'Florida', 'SOMEWHERE IN YYY', '33133');
insert into public.address (city, deleted, state, street, zip_code) values ('Sarasota', false, 'Florida', 'SOMEWHERE IN ZZZ', '33133');
insert into public.address (city, deleted, state, street, zip_code) values ('Fairfield', false, 'IA', '1000N 4th Street', '52557');
insert into public.address (city, deleted, state, street, zip_code) values ('Fairfield', false, 'IA', '1000N 4th Street', '52557');
insert into public.address (city, deleted, state, street, zip_code) values ('Fairfield', false, 'IA', '1000N 4th Street', '52557');
insert into public.address (city, deleted, state, street, zip_code) values ('Kathmandu', false, 'Florida', 'SOMEWHERE IN XXX', '33133');
insert into public.address (city, deleted, state, street, zip_code) values ('Bhaktapur', false, 'Florida', 'SOMEWHERE IN YYY', '33133');
insert into public.address (city, deleted, state, street, zip_code) values ('Dang', false, 'Florida', 'SOMEWHERE IN ZZZ', '33133');
insert into public.address (city, deleted, state, street, zip_code) values ('Germantown', false, 'IA', '1000N 4th Street', '52557');
insert into public.address (city, deleted, state, street, zip_code) values ('Vegas', false, 'IA', '1000N 4th Street', '52557');
insert into public.address (city, deleted, state, street, zip_code) values ('Des Moines', false, 'Iowa', '1000N 4TH ST BLD 142', '52557');


insert into public.users (user_type, last_lock_time, email, email_verification_attempts, email_verification_token, email_verification_token_expiry, first_name, invalid_login_count, is_activated, is_deleted, is_email_verified, is_locked, last_name, password, department, faculty_id, completion_year, entry_year, major, program, student_id, address_id) values ('ADMIN', null, 'admin@miu.edu', 0, null, null, 'Admin', 0, true, false, true, false, '', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', null, null, null, null, null, null, null, 1);
insert into public.users (user_type, last_lock_time, email, email_verification_attempts, email_verification_token, email_verification_token_expiry, first_name, invalid_login_count, is_activated, is_deleted, is_email_verified, is_locked, last_name, password, department, faculty_id, completion_year, entry_year, major, program, student_id, address_id) values ('STUDENT', null, 'student1@miu.edu', 0, null, null, 'Bipul', 0, true, false, true, false, 'Ranjitkar', '$2a$10$0.h6IUQrpTvDvKLmgKsaOOUPjZj/QT357DrqVxDlfXE6DWDfmdyRa', null, null, '2025', '2022', 'CS', 'ComPro', '615185', 8);
insert into public.users (user_type, last_lock_time, email, email_verification_attempts, email_verification_token, email_verification_token_expiry, first_name, invalid_login_count, is_activated, is_deleted, is_email_verified, is_locked, last_name, password, department, faculty_id, completion_year, entry_year, major, program, student_id, address_id) values ('FACULTY', null, 'faculty3@miu.edu', 0, null, null, 'Obinna', 0, true, false, true, false, 'Kalu', '$2a$10$yIO1WFssp78sR5PWsFNJde9x2Immi0edRNxs1torfSeUPaJGNDAP6', null, null, null, null, null, null, null, 4);
insert into public.users (user_type, last_lock_time, email, email_verification_attempts, email_verification_token, email_verification_token_expiry, first_name, invalid_login_count, is_activated, is_deleted, is_email_verified, is_locked, last_name, password, department, faculty_id, completion_year, entry_year, major, program, student_id, address_id) values ('STUDENT', null, 'student2@miu.edu', 0, null, null, 'Hridaya', 0, true, false, true, false, 'kandel', '$2a$10$nLAgm5WMTLIJHRjqjLPVlOhkh7tSOb3/H/9xeMETELQWrBojPIP3O', null, null, '2025', '2022', 'CS', 'ComPro', '615186', 9);
insert into public.users (user_type, last_lock_time, email, email_verification_attempts, email_verification_token, email_verification_token_expiry, first_name, invalid_login_count, is_activated, is_deleted, is_email_verified, is_locked, last_name, password, department, faculty_id, completion_year, entry_year, major, program, student_id, address_id) values ('STUDENT', null, 'student3@miu.edu', 0, null, null, 'Fahim', 0, true, false, true, false, 'Uddin', '$2a$10$sZMIdp9YJ6Be7rtjdIX6kuaMEZg9AzPggGmvNd5DfklKx52I6WHYa', null, null, '2025', '2022', 'CS', 'ComPro', '615187', 6);
insert into public.users (user_type, last_lock_time, email, email_verification_attempts, email_verification_token, email_verification_token_expiry, first_name, invalid_login_count, is_activated, is_deleted, is_email_verified, is_locked, last_name, password, department, faculty_id, completion_year, entry_year, major, program, student_id, address_id) values ('STUDENT', null, 'student@miu.edu', 0, null, null, 'Sujit', 0, true, false, true, false, 'Chanda', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', null, null, '2025', '2022', 'CS', 'ComPro', '615188', 2);
insert into public.users (user_type, last_lock_time, email, email_verification_attempts, email_verification_token, email_verification_token_expiry, first_name, invalid_login_count, is_activated, is_deleted, is_email_verified, is_locked, last_name, password, department, faculty_id, completion_year, entry_year, major, program, student_id, address_id) values ('FACULTY', null, 'faculty1@miu.edu', 0, null, null, 'Asad', 0, true, false, true, false, 'Maluf', '$2a$10$AYvMJ2MnFqdCi3Htm0XWkeHivz7EQaA3oeZFl1ip.Vy93zQMwdi/G', 'ComPro', '123456', null, null, null, null, null, 5);
insert into public.users (user_type, last_lock_time, email, email_verification_attempts, email_verification_token, email_verification_token_expiry, first_name, invalid_login_count, is_activated, is_deleted, is_email_verified, is_locked, last_name, password, department, faculty_id, completion_year, entry_year, major, program, student_id, address_id) values ('FACULTY', null, 'uinan@miu.edu', 0, null, null, 'Umur', 0, true, false, true, false, 'Inan', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', null, null, null, null, null, null, null, 3);
insert into public.users (user_type, last_lock_time, email, email_verification_attempts, email_verification_token, email_verification_token_expiry, first_name, invalid_login_count, is_activated, is_deleted, is_email_verified, is_locked, last_name, password, department, faculty_id, completion_year, entry_year, major, program, student_id, address_id) values ('FACULTY', null, 'faculty2@miu.edu', 0, null, null, 'Siamak ', 0, true, false, true, false, 'Tavakoli', '$2a$10$5i65ffe/4J1UCoZKswVh/eZ9w3paba3OaeHQxFv4qFx2uMifwPdvi', 'ComPro', '1234567', null, null, null, null, null, 7);


insert into public.student_tag (tag, student_id) values ('java', 4);
insert into public.student_tag (tag, student_id) values ('hibernate', 4);
insert into public.student_tag (tag, student_id) values ('spring boot', 4);
insert into public.student_tag (tag, student_id) values ('java', 5);
insert into public.student_tag (tag, student_id) values ('hibernate', 5);
insert into public.student_tag (tag, student_id) values ('spring boot', 5);
insert into public.student_tag (tag, student_id) values ('java', 6);
insert into public.student_tag (tag, student_id) values ('hibernate', 6);
insert into public.student_tag (tag, student_id) values ('spring boot', 6);
insert into public.student_tag (tag, student_id) values ('java', 2);
insert into public.student_tag (tag, student_id) values ('hibernate', 2);
insert into public.student_tag (tag, student_id) values ('spring boot', 2);


insert into public.job_experience (company, created_date, description, end_date, is_deleted, start_date, title, user_id) values ('Oscillosoft Pty Ltd.', '2022-12-22 15:39:03.547796', 'Developed application using Spring, hibernate ', '2022-12-31 00:00:00.000000', false, '2019-12-01 00:00:00.000000', 'Software Engineer', 4);
insert into public.job_experience (company, created_date, description, end_date, is_deleted, start_date, title, user_id) values ('Data Edge Ltd', '2022-12-22 15:39:53.206672', 'Developed application using Spring, hibernate', '2022-12-31 00:00:00.000000', false, '2021-08-01 00:00:00.000000', 'Software Engineer', 4);
insert into public.job_experience (company, created_date, description, end_date, is_deleted, start_date, title, user_id) values ('Oscillosoft Pty Ltd.', '2022-12-22 15:52:58.590938', 'Developed application using java', '2022-12-31 00:00:00.000000', false, '2022-12-01 00:00:00.000000', 'Software Engineer', 5);
insert into public.job_experience (company, created_date, description, end_date, is_deleted, start_date, title, user_id) values ('Oscillosoft Pty Ltd.', '2022-12-22 15:56:51.282892', 'Developed application using java', '2022-12-31 00:00:00.000000', false, '2022-12-01 00:00:00.000000', 'Software Engineer', 6);
insert into public.job_experience (company, created_date, description, end_date, is_deleted, start_date, title, user_id) values ('Oscillosoft Pty Ltd.', '2022-12-22 16:01:48.196312', 'Developed application using java', '2022-12-31 00:00:00.000000', false, '2022-12-01 00:00:00.000000', 'Software Engineer', 2);


insert into public.comment (comment, created_date, is_deleted, faculty_id, student_id) values ('He is good in Spring and Hibernate', '2022-12-22 15:00:45.087731', false, 3, 2);
insert into public.comment (comment, created_date, is_deleted, faculty_id, student_id) values ('test', '2022-12-22 15:04:24.377649', false, 3, 2);
insert into public.comment (comment, created_date, is_deleted, faculty_id, student_id) values ('He is good in react', '2022-12-22 15:45:51.580398', false, 7, 5);
insert into public.comment (comment, created_date, is_deleted, faculty_id, student_id) values ('he is good in react', '2022-12-22 15:46:04.412049', false, 7, 6);
insert into public.comment (comment, created_date, is_deleted, faculty_id, student_id) values ('He is good in spring, hibernate and react', '2022-12-22 15:46:35.716418', false, 7, 4);


insert into public.job_advertisement (application_end_date, company, contact, created_date, file_path, is_deleted, job_description, title, address_id, owner_id) values ('2022-12-31', 'Test Company', '2343423434', '2022-12-20 23:23:00.000000', null, false, 'afgasdfasdfasdfasdfasfd', 'Software Engineer', 12, 2);
insert into public.job_advertisement (application_end_date, company, contact, created_date, file_path, is_deleted, job_description, title, address_id, owner_id) values ('2022-12-31', 'Oscillosoft Pty Ltd', '1234567890', '2022-12-22 15:34:51.760561', null, false, 'We want 4 years experienced java developer', 'Software Engineer', 13, 4);
insert into public.job_advertisement (application_end_date, company, contact, created_date, file_path, is_deleted, job_description, title, address_id, owner_id) values ('2022-12-31', 'Data Edge Ltd.', '1234567890', '2022-12-22 15:35:40.355225', null, false, 'We want 4 years experienced java developer', 'Software Engineer', 14, 4);
insert into public.job_advertisement (application_end_date, company, contact, created_date, file_path, is_deleted, job_description, title, address_id, owner_id) values ('2022-12-31', 'Selise Recon Ltd.', '1234567890', '2022-12-22 15:36:18.227590', null, false, 'We want 4 years experienced java developer', 'Software Engineer', 15, 4);
insert into public.job_advertisement (application_end_date, company, contact, created_date, file_path, is_deleted, job_description, title, address_id, owner_id) values ('2022-12-31', 'Oscillosoft Pty Ltd', '1234567890', '2022-12-22 15:53:58.580702', null, false, 'We want 4 years experienced java developer', 'Software Engineer', 16, 5);
insert into public.job_advertisement (application_end_date, company, contact, created_date, file_path, is_deleted, job_description, title, address_id, owner_id) values ('2022-12-31', 'Oscillosoft Pty Ltd', '1234567890', '2022-12-22 15:57:24.834449', null, false, 'We want 4 years experienced java developer', 'Software Engineer', 11, 6);


insert into public.job_tag (tag, job_id) values ('miu', 1);
insert into public.job_tag (tag, job_id) values ('java', 1);
insert into public.job_tag (tag, job_id) values ('spring', 1);
insert into public.job_tag (tag, job_id) values ('java', 2);
insert into public.job_tag (tag, job_id) values ('hibernate', 2);
insert into public.job_tag (tag, job_id) values ('spring boot', 2);
insert into public.job_tag (tag, job_id) values ('java', 3);
insert into public.job_tag (tag, job_id) values ('hibernate', 3);
insert into public.job_tag (tag, job_id) values ('spring boot', 3);
insert into public.job_tag (tag, job_id) values ('java', 4);
insert into public.job_tag (tag, job_id) values ('hibernate', 4);
insert into public.job_tag (tag, job_id) values ('spring boot', 4);
insert into public.job_tag (tag, job_id) values ('java', null);
insert into public.job_tag (tag, job_id) values ('hibernate', null);
insert into public.job_tag (tag, job_id) values ('spring boot', null);
insert into public.job_tag (tag, job_id) values ('java', 5);
insert into public.job_tag (tag, job_id) values ('hibernate', 5);
insert into public.job_tag (tag, job_id) values ('spring boot', 5);
insert into public.job_tag (tag, job_id) values ('java', 6);
insert into public.job_tag (tag, job_id) values ('hibernate', 6);
insert into public.job_tag (tag, job_id) values ('spring boot', 6);


-- insert into public.job_application (id, applied_time, applicant_id, job_id) values (1, '2022-12-22 15:58:16.909038', 5, 1);
-- insert into public.job_application (id, applied_time, applicant_id, job_id) values (2, '2022-12-22 15:53:08.562903', 5, 2);
-- insert into public.job_application (id, applied_time, applicant_id, job_id) values (3, '2022-12-22 15:53:13.051376', 5, 3);
-- insert into public.job_application (id, applied_time, applicant_id, job_id) values (4, '2022-12-22 15:53:17.146016', 5, 4);
-- insert into public.job_application (id, applied_time, applicant_id, job_id) values (5, '2022-12-22 15:54:34.987895', 5, 5);
-- insert into public.job_application (id, applied_time, applicant_id, job_id) values (6, '2022-12-22 15:57:31.990147', 6, 6);