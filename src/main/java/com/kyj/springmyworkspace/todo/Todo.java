package com.kyj.springmyworkspace.todo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

//@Entity = �����ͺ��̽��� ���̺�� ������ (mapping)
// ORM(Object Relational Mapping)
// : ��ü�� ���̺��� �����Ѵ�.

// Todo class -> todo table�� ����
// class�� ���̺��� pascal-case -> snake-case�� ����
// StudentInfo class -> student_info table

//�ʵ�� �÷��� camel-case -> snake-case�� ����
//createdTime filed -> created_time column

// �ڵ� ���迡 ���� �����ͺ��̽� ������ ��������� ���
// auto-migration

@Data
@Entity
public class Todo {

	// Id - ���̺��� PK(�����̸Ӹ� Ű: ����/��ǥ �÷�)
	@Id

	// @GeneratedValue -> �ʵ尪 ���� ��� ����, IDENTITY�� �����ͺ��̽��� �ڵ��������� ���
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String memo;
	private Long createdTime;

}
