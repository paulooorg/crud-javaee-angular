export interface Page<T> {
    content: T[];
    totalCount: number;
    numberOfPages: number;
    currentPage: number;
}