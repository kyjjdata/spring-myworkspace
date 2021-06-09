package com.kyj.springmyworkspace.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// ������ ���� �������̽� ����
// JpaRepository<��ƼƼŸ��, idŸ��>

// �������̽��� �ν��Ͻ� ������ �� �ȴ�.

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

}
