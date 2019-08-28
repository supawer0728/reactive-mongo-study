package com.parfait.study.reactivemongo.post.entity;

import lombok.Data;

@Data
public class User {

	private Long id;
	private String name;
	private String username;
	private String phone;
	private String website;

	private Address address;
	private Company company;

	@Data
	public static class Address {
		private String zipcode;
		private Location geo;

		@Data
		public static class Location {
			private Double lat;
			private Double lng;
		}
	}

	@Data
	public static class Company {
		private String name;
		private String catchPhrase;
		private String bs;
	}
}
