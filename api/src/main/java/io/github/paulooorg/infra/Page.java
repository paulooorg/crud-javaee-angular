package io.github.paulooorg.infra;

import java.util.List;

public class Page<T> {
	private List<T> content;
	
	private Long totalCount;
	
	private Long numberOfPages;
	
	private Long currentPage;

	public Page(List<T> content, Long totalCount, Long numberOfPages, Long currentPage) {
		this.content = content;
		this.totalCount = totalCount;
		this.numberOfPages = numberOfPages;
		this.currentPage = currentPage;
	}
	
	public List<T> getContent() {
		return content;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public Long getNumberOfPages() {
		return numberOfPages;
	}

	public Long getCurrentPage() {
		return currentPage;
	}
}
