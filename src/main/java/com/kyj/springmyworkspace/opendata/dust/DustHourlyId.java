package com.kyj.springmyworkspace.opendata.dust;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // �Ű����� ���� �⺻ ������
@AllArgsConstructor // ��� �ʵ带 �ʱ�ȭ�ϴ� ������
public class DustHourlyId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1743176488971107945L;
	private String dataTime;
	private String itemCode;
}
