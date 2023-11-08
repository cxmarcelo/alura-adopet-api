package br.com.mcb.adopet.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.mcb.adopet.model.ShelterModel;

public record ShelterDto(Long id, String name, String phone, String email) {

	public ShelterDto(ShelterModel shelter) {
		this(
				shelter.getId(), 
				shelter.getName(), 
				shelter.getPhone(),
				shelter.getEmail()
				);
	}

	public static List<ShelterDto> convert(List<ShelterModel> list) {
		return list.stream().map(pet -> new ShelterDto(pet)).collect(Collectors.toList());
	}

}
