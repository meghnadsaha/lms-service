
CREATE TABLE courses (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    instructor_id INT NOT NULL
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    passwordHash  VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

ALTER TABLE users
ADD COLUMN role_id INTEGER REFERENCES roles(id);

SELECT * FROM users ;

CREATE TABLE enrollments (
    id SERIAL PRIMARY KEY,
    course_id INT NOT NULL,
    student_id INT NOT NULL,
    enrollment_date DATE NOT NULL,
    FOREIGN KEY (course_id) REFERENCES courses(id),
    FOREIGN KEY (student_id) REFERENCES users(id)
);

CREATE TABLE assignments (
    id SERIAL PRIMARY KEY,
    course_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    due_date DATE NOT NULL,
    FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE content (
    id SERIAL PRIMARY KEY,
    course_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE grades (
    id SERIAL PRIMARY KEY,
    assignment_id INT NOT NULL,
    student_id INT NOT NULL,
    grade DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (assignment_id) REFERENCES assignments(id),
    FOREIGN KEY (student_id) REFERENCES users(id)
);

CREATE TABLE forum_posts (
    id SERIAL PRIMARY KEY,
    course_id INT NOT NULL,
    author_id INT NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (course_id) REFERENCES courses(id),
    FOREIGN KEY (author_id) REFERENCES users(id)
);

CREATE TABLE forum_comments (
    id SERIAL PRIMARY KEY,
    post_id INT NOT NULL,
    author_id INT NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES forum_posts(id),
    FOREIGN KEY (author_id) REFERENCES users(id)
);



-- Insert data into the users table
INSERT INTO users (username, passwordHash, email, role)
VALUES
    ('john_doe', 'password123', 'john.doe@example.com', 'INSTRUCTOR'),
    ('jane_smith', 'password456', 'jane.smith@example.com', 'STUDENT'),
    ('alice_wonderland', 'password789', 'alice.wonderland@example.com', 'ADMIN');

INSERT INTO roles(name) VALUES
    ('ROLE_USER'),
    ('ROLE_MODERATOR'),
    ('ROLE_ADMIN');
-- Insert data into the courses table
INSERT INTO courses (title, description, instructor_id)
VALUES
    ('Introduction to Programming', 'Learn the basics of programming', 2),
    ('Database Management', 'Explore the world of databases', 2),
    ('Web Development', 'Build dynamic websites', 3);


-- Insert data into the enrollments table
INSERT INTO enrollments (course_id, student_id, enrollment_date)
VALUES
    (1, 2, '2024-01-15'),
    (1, 3, '2024-01-20'),
    (2, 3, '2024-02-10');

-- Insert data into the assignments table
INSERT INTO assignments (course_id, title, due_date)
VALUES
    (1, 'Assignment 1', '2024-02-01'),
    (1, 'Assignment 2', '2024-02-15'),
    (2, 'Database Design Project', '2024-03-01');

-- Insert data into the content table
INSERT INTO content (course_id, title, type)
VALUES
    (1, 'Introduction to Programming', 'video'),
    (1, 'Variables and Data Types', 'text'),
    (2, 'Relational Databases', 'video');

-- Insert data into the grades table
INSERT INTO grades (assignment_id, student_id, grade)
VALUES
    (1, 2, 85.5),
    (1, 3, 78.0),
    (2, 3, 92.5);

-- Insert data into the forum_posts table
INSERT INTO forum_posts (course_id, author_id, content)
VALUES
    (1, 2, 'I have a question about Assignment 1'),
    (1, 3, 'Can someone help me with Assignment 2?');

-- Insert data into the forum_comments table
INSERT INTO forum_comments (post_id, author_id, content)
VALUES
    (1, 1, 'Sure, what do you need help with?'),
    (2, 1, 'I can assist you with that.');
