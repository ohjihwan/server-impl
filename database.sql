-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS board_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE board_db;

-- 회원 테이블
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 게시글 테이블
CREATE TABLE posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    author_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 회원 조회 쿼리
-- SELECT * FROM users WHERE username = #{username};
-- SELECT * FROM users WHERE id = #{id};

-- 회원 가입 쿼리
-- INSERT INTO users (username, password, email, name) VALUES (#{username}, #{password}, #{email}, #{name});

-- 회원 정보 수정 쿼리
-- UPDATE users SET email = #{email}, name = #{name}, updated_at = CURRENT_TIMESTAMP WHERE id = #{id};

-- 게시글 목록 조회 쿼리 (페이징)
-- SELECT p.*, u.username as author_name FROM posts p JOIN users u ON p.author_id = u.id ORDER BY p.created_at DESC LIMIT #{limit} OFFSET #{offset};

-- 게시글 총 개수 조회 쿼리
-- SELECT COUNT(*) FROM posts;

-- 게시글 상세 조회 쿼리
-- SELECT p.*, u.username as author_name FROM posts p JOIN users u ON p.author_id = u.id WHERE p.id = #{id};

-- 게시글 작성 쿼리
-- INSERT INTO posts (title, content, author_id) VALUES (#{title}, #{content}, #{authorId});

-- 게시글 수정 쿼리
-- UPDATE posts SET title = #{title}, content = #{content}, updated_at = CURRENT_TIMESTAMP WHERE id = #{id} AND author_id = #{authorId};

-- 게시글 삭제 쿼리
-- DELETE FROM posts WHERE id = #{id} AND author_id = #{authorId};
