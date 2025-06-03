import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "environments/environment";

@Injectable({ providedIn: 'root' })
export class HttpService {

    constructor(private http: HttpClient) {}

    /**
     * Performs a GET request
     * @param endpoint - The API endpoint without the base URL
     * @param params - Optional query parameters
     * @returns Observable of the response
     */
    public get<T>(endpoint: string, params?: HttpParams): Observable<T> {
        return this.http.get<T>(environment.apiUrl + endpoint, { params });
    }

    /**
     * Performs a POST request
     * @param endpoint - The API endpoint without the base URL
     * @param body - The request body
     * @returns Observable of the response
     */
    public post<T>(endpoint: string, body: any): Observable<T> {
        return this.http.post<T>(environment.apiUrl + endpoint, body);
    }

    /**
     * Performs a PUT request
     * @param endpoint - The API endpoint without the base URL
     * @param body - The request body
     * @returns Observable of the response
     */
    public put<T>(endpoint: string, body: any): Observable<T> {
        return this.http.put<T>(environment.apiUrl + endpoint, body);
    }

    /**
     * Performs a DELETE request
     * @param endpoint - The API endpoint without the base URL
     * @returns Observable of the response
     */
    public delete<T>(endpoint: string): Observable<T> {
        return this.http.delete<T>(environment.apiUrl + endpoint);
    }

    /**
     * Performs a PATCH request
     * @param endpoint - The API endpoint without the base URL
     * @param body - The request body
     * @returns Observable of the response
     */
    public patch<T>(endpoint: string, body: any): Observable<T> {
        return this.http.patch<T>(environment.apiUrl + endpoint, body);
    }
}