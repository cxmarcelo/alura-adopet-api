package br.com.mcb.adopet.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.mcb.adopet.model.PetModel;
import br.com.mcb.adopet.model.PetTypeEnum;

public record PetDto(Long id, PetTypeEnum type, String name, String race, Integer age) {

	public PetDto(PetModel petModel) {
		this(petModel.getId(), petModel.getType(), petModel.getName(), petModel.getRace(), petModel.getAge());
	}

	public static List<PetDto> convert(List<PetModel> list) {
		return list.stream().map(pet -> new PetDto(pet)).collect(Collectors.toList());
	}

}
