package com.study.springboot;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContentDto {
	private int id;
	//폼값이 null일때 메세지를 출력한다.
	@NotNull(message="writer is null.")
	//폼값이 empty일때 메세지를 출력한다.
	@NotEmpty(message="writer is empty")
	@Size(min=3, max=10, message="writer min3, max=10")
	private String writer;
	@NotNull(message="content is null.")
	@NotEmpty(message="content is empty.")
	private String content;
}
