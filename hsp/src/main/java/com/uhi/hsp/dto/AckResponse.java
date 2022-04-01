package com.uhi.hsp.dto;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * InlineResponse200
 */
@Validated
public class AckResponse {
	@JsonProperty("message")
	private Ack message = null;

	@JsonProperty("error")
	private Error error = null;

	public AckResponse message(Ack message) {
		this.message = message;
		return this;
	}

	/**
	 * Get message
	 * 
	 * @return message
	 **/

	@NotNull

	@Valid
	public Ack getMessage() {
		return message;
	}

	public void setMessage(Ack message) {
		this.message = message;
	}

	public AckResponse error(Error error) {
		this.error = error;
		return this;
	}

	/**
	 * Get error
	 * 
	 * @return error
	 **/

	@Valid
	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AckResponse inlineResponse200 = (AckResponse) o;
		return Objects.equals(this.message, inlineResponse200.message)
				&& Objects.equals(this.error, inlineResponse200.error);
	}

	@Override
	public int hashCode() {
		return Objects.hash(message, error);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse200 {\n");

		sb.append("    message: ").append(toIndentedString(message)).append("\n");
		sb.append("    error: ").append(toIndentedString(error)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
