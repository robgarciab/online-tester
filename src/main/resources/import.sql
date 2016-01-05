insert into User(name, password, active) values ('roberto', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', true);
insert into User(name, password, active) values ('gabriel', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', true);
insert into User(name, password, active) values ('eduardo', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', true);

insert into User_Roles(user_id, roles) values (1, 'STUDENT');
insert into User_Roles(user_id, roles) values (1, 'ADMIN');
insert into User_Roles(user_id, roles) values (2, 'STUDENT');
insert into User_Roles(user_id, roles) values (3, 'STUDENT');

insert into Exam(max_points, description, instructions, min_points_to_pass) values (10, 'Math 1 Final Exam', 'The exam contains 5 questions you will get 2 points for each correct answer, make sure you have save each answer before submit.', 6);
insert into Exam(max_points, description, instructions, min_points_to_pass) values (10, 'Algoritms 1 Final Exam', 'The exam contains 5 questions you will get 2 points for each correct answer, make sure you have save each answer before submit.', 6);
insert into Exam(max_points, description, instructions, min_points_to_pass) values (10, 'Literature 1 Final Exam', 'The exam contains 5 questions you will get 2 points for each correct answer, make sure you have save each answer before submit.', 6);
insert into Exam(max_points, description, instructions, min_points_to_pass) values (10, 'Biology 1 Final Exam', 'The exam contains 5 questions you will get 2 points for each correct answer, make sure you have save each answer before submit.', 6);

--timeToComplete its in seconds
--Exams 1 & 2 expire 5 days from CURRENT_DATE. Exam 3 has already expired
--Available Exam
insert into User_Exam(user_id, exam_id, expire_date, time_to_complete) values (1, 1, TIMESTAMPADD(SQL_TSI_DAY, 5, CURRENT_TIMESTAMP), 60*60);
insert into User_Exam(user_id, exam_id, expire_date, time_to_complete) values (2, 1, TIMESTAMPADD(SQL_TSI_DAY, 5, CURRENT_TIMESTAMP), 60*60);
--Available Exam
insert into User_Exam(user_id, exam_id, expire_date, time_to_complete) values (1, 2, TIMESTAMPADD(SQL_TSI_DAY, 5, CURRENT_TIMESTAMP), 60*60);
insert into User_Exam(user_id, exam_id, expire_date, time_to_complete) values (2, 2, TIMESTAMPADD(SQL_TSI_DAY, 5, CURRENT_TIMESTAMP), 60*60);
--Expired Exam
insert into User_Exam(user_id, exam_id, expire_date, time_to_complete) values (1, 3, TO_DATE('10/08/2015','DD/MM/YYYY'), 60*60);
insert into User_Exam(user_id, exam_id, expire_date, time_to_complete) values (2, 3, TO_DATE('10/08/2015','DD/MM/YYYY'), 60*60);

--Math 1 Final Exam Questions
insert into Question(question_type, position, description) values ('SINGLE_CHOICE', 1, 'A car averages 27 miles per gallon. If gas costs $4.04 per gallon, which of the following is closest to how much the gas would cost for this car to travel 2,727 typical miles. Correct answer: $408.04');
insert into Question(question_type, position, description) values ('SINGLE_CHOICE', 2, 'When x = 3 and y = 5, by how much does the value of 3x^2 - 2y exceed the value of 2x^2 - 3y. Correct answer: 14');
insert into Question(question_type, position, description) values ('SINGLE_CHOICE', 3, 'What is the value of x when 2x + 3 = 3x - 4. Correct answer: 7');
insert into Question(question_type, position, description) values ('SINGLE_CHOICE', 4, 'What is the greatest common factor of 42, 126, and 210. Correct answer: 2');
insert into Question(question_type, position, description) values ('SINGLE_CHOICE', 5, 'How many irrational numbers are there between 1 and 6. Correct answer: Infinitely many');

insert into Exam_Question(exam_id, question_id, sequence, points) values (1, 1, 1, 2);
insert into Exam_Question(exam_id, question_id, sequence, points) values (1, 2, 2, 2);
insert into Exam_Question(exam_id, question_id, sequence, points) values (1, 3, 3, 2);
insert into Exam_Question(exam_id, question_id, sequence, points) values (1, 4, 4, 2);
insert into Exam_Question(exam_id, question_id, sequence, points) values (1, 5, 5, 2);

--Math 1 Final Exam Question 1 options
insert into Question_Choice(question_id, position, description, correct) values (1, 1, '$44.44', false);
insert into Question_Choice(question_id, position, description, correct) values (1, 2, '$109.08', false);
insert into Question_Choice(question_id, position, description, correct) values (1, 3, '$118.80', false);
insert into Question_Choice(question_id, position, description, correct) values (1, 4, '$408.04', true);
insert into Question_Choice(question_id, position, description, correct) values (1, 5, '$444.40', false);

--Math 1 Final Exam Question 2 options
insert into Question_Choice(question_id, position, description, correct) values (2, 1, '4', false);
insert into Question_Choice(question_id, position, description, correct) values (2, 2, '14', true);
insert into Question_Choice(question_id, position, description, correct) values (2, 3, '16', false);
insert into Question_Choice(question_id, position, description, correct) values (2, 4, '20', false);
insert into Question_Choice(question_id, position, description, correct) values (2, 5, '50', false);

--Math 1 Final Exam Question 3 options
insert into Question_Choice(question_id, position, description, correct) values (3, 1, '3', false);
insert into Question_Choice(question_id, position, description, correct) values (3, 2, '-1/5', false);
insert into Question_Choice(question_id, position, description, correct) values (3, 3, '1', false);
insert into Question_Choice(question_id, position, description, correct) values (3, 4, '1/5', false);
insert into Question_Choice(question_id, position, description, correct) values (3, 5, '7', true);

--Math 1 Final Exam Question 4 options
insert into Question_Choice(question_id, position, description, correct) values (4, 1, '2', true);
insert into Question_Choice(question_id, position, description, correct) values (4, 2, '6', false);
insert into Question_Choice(question_id, position, description, correct) values (4, 3, '14', false);
insert into Question_Choice(question_id, position, description, correct) values (4, 4, '21', false);
insert into Question_Choice(question_id, position, description, correct) values (4, 5, '42', false);

--Math 1 Final Exam Question 5 options
insert into Question_Choice(question_id, position, description, correct) values (5, 1, '1', false);
insert into Question_Choice(question_id, position, description, correct) values (5, 2, '3', false);
insert into Question_Choice(question_id, position, description, correct) values (5, 3, '4', false);
insert into Question_Choice(question_id, position, description, correct) values (5, 4, '10', false);
insert into Question_Choice(question_id, position, description, correct) values (5, 5, 'Infinitely many', true);

--Algoritms 1 Final Exam Questions
--Question taken from: http://www.actstudent.org/sampletest/math/math_01.html
insert into Question(question_type, position, description) values ('MULTIPLE_CHOICE', 1, 'The memory address of the first element of an array is called:');
insert into Question(question_type, position, description) values ('MULTIPLE_CHOICE', 2, 'The memory address of fifth element of an array can be calculated by the formula:');
insert into Question(question_type, position, description) values ('MULTIPLE_CHOICE', 3, 'Which of the following data structures are indexed structures?');
insert into Question(question_type, position, description) values ('MULTIPLE_CHOICE', 4, 'Which of the following is not the required condition for binary search algorithm?');
insert into Question(question_type, position, description) values ('MULTIPLE_CHOICE', 5, 'Which of the following is not a limitation of binary search algorithm?');

insert into Exam_Question(exam_id, question_id, sequence, points) values (2, 6, 1, 2);
insert into Exam_Question(exam_id, question_id, sequence, points) values (2, 7, 2, 2);
insert into Exam_Question(exam_id, question_id, sequence, points) values (2, 8, 3, 2);
insert into Exam_Question(exam_id, question_id, sequence, points) values (2, 9, 4, 2);
insert into Exam_Question(exam_id, question_id, sequence, points) values (2, 10, 5, 2);

--Algoritms 1 Final Exam Question 1 options
insert into Question_Choice(question_id, position, description, correct) values (6, 1, 'Floor address', false);
insert into Question_Choice(question_id, position, description, correct) values (6, 2, 'Foundation address', false);
insert into Question_Choice(question_id, position, description, correct) values (6, 3, 'First address', false);
insert into Question_Choice(question_id, position, description, correct) values (6, 4, 'Base address', true);

--Algoritms 1 Final Exam Question 2 options
insert into Question_Choice(question_id, position, description, correct) values (7, 1, 'LOC(Array[5]=Base(Array)+w(5-lower bound), where w is the number of words per memory cell for the array', true);
insert into Question_Choice(question_id, position, description, correct) values (7, 2, 'LOC(Array[5])=Base(Array[5])+(5-lower bound), where w is the number of words per memory cell for the array', false);
insert into Question_Choice(question_id, position, description, correct) values (7, 3, 'LOC(Array[5])=Base(Array[4])+(5-Upper bound), where w is the number of words per memory cell for the array', false);
insert into Question_Choice(question_id, position, description, correct) values (7, 4, 'None of above', false);

--Algoritms 1 Final Exam Question 3 options
insert into Question_Choice(question_id, position, description, correct) values (8, 1, 'Linear arrays', true);
insert into Question_Choice(question_id, position, description, correct) values (8, 2, 'Linked lists', false);
insert into Question_Choice(question_id, position, description, correct) values (8, 3, 'Both of above', false);
insert into Question_Choice(question_id, position, description, correct) values (8, 4, 'None of above', false);

--Algoritms 1 Final Exam Question 4 options
insert into Question_Choice(question_id, position, description, correct) values (9, 1, 'The list must be sorted', false);
insert into Question_Choice(question_id, position, description, correct) values (9, 2, 'There should be the direct access to the middle element in any sublist', false);
insert into Question_Choice(question_id, position, description, correct) values (9, 3, 'There must be mechanism to delete and/or insert elements in list', true);
insert into Question_Choice(question_id, position, description, correct) values (9, 4, 'None of above', false);

--Algoritms 1 Final Exam Question 5 options
insert into Question_Choice(question_id, position, description, correct) values (10, 1, 'Must use a sorted array', false);
insert into Question_Choice(question_id, position, description, correct) values (10, 2, 'Requirement of sorted array is expensive when a lot of insertion and deletions are needed', false);
insert into Question_Choice(question_id, position, description, correct) values (10, 3, 'There must be a mechanism to access middle element directly', false);
insert into Question_Choice(question_id, position, description, correct) values (10, 4, 'Binary search algorithm is not efficient when the data elements are more than 1000', true);
