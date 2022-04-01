package com.uhi.hsp.service;

import com.dhp.sdk.beans.*;
import com.fasterxml.classmate.GenericType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uhi.hsp.dto.EuaRequestBody;
import com.uhi.hsp.dto.HspRequestBody;
import com.uhi.hsp.dto.OnSelect;
import com.uhi.hsp.model.Categories;
import com.uhi.hsp.model.Fulfillments;
import com.uhi.hsp.model.Provider;
import com.uhi.hsp.repo.FulfillmentsRepo;
import com.uhi.hsp.repo.ProviderRepo;
import org.apache.poi.ss.formula.functions.T;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatusService {
	private final ProviderRepo _providerRepo;

	@Value("classpath:static/on_search.json")
	private Resource on_searchFile;

	@Value("classpath:static/on_select.json")
	private Resource on_selectFile;

	@Value("classpath:static/on_init.json")
	private Resource on_initFile;

	@Value("classpath:static/on_confirm.json")
	private Resource on_confirmFile;

	@Value("classpath:static/on_status.json")
	private Resource on_statusFile;

	final ObjectMapper mapper;

	final ModelMapper modelMapper;
	@Autowired
	private FulfillmentsRepo fulfillmentsRepo; 

	public StatusService(ObjectMapper mapper, ProviderRepo providerRepo, ModelMapper modelMapper) {
		this.mapper = mapper;
		this._providerRepo = providerRepo;
		this.modelMapper = modelMapper;
	}

	public OnTBody mapSelect(HspRequestBody req) throws IOException {
		/*
		 * String messageId = req.getContext().getMessage_id(); EuaRequestBody body =
		 * new EuaRequestBody(); body.setContext(req.getContext());
		 * body.getContext().setAction("on_select"); //
		 * body.setMessage(_selectRepo.findAll().get(0).getMessage()); //EuaRequestBody
		 * body = mapper.readValue( on_selectFile.getFile(), EuaRequestBody.class);
		 * body.getContext().setMessage_id(messageId);
		 */
		return getProviderById(req);
	}

	public OnTBody mapInit(HspRequestBody req) throws IOException {
		String messageId = req.getContext().getMessage_id();
		OnTBody body = mapper.readValue(on_initFile.getFile(), OnTBody.class);
		body.getContext().setMessage_id(messageId);
		return body;

	}

	public OnTBody mapStatus(HspRequestBody req) throws IOException {
		String messageId = req.getContext().getMessage_id();
		OnTBody body = mapper.readValue(on_statusFile.getFile(), OnTBody.class);
		body.getContext().setMessage_id(messageId);
		return body;

	}

	public OnTBody mapConfirm(HspRequestBody req) throws IOException {
		String messageId = req.getContext().getMessage_id();
		OnTBody body = mapper.readValue(on_confirmFile.getFile(), OnTBody.class);
		body.getContext().setMessage_id(messageId);
		return body;

	}

	public EuaRequestBody mapSearch(HspRequestBody req) throws IOException {
		return getSearchByProviderName(req);
	}

	private EuaRequestBody getSearchByProviderName(HspRequestBody req) {
		EuaRequestBody euaRequestBody = new EuaRequestBody();
		euaRequestBody = extractedContext(req, euaRequestBody);
		System.out.println("__________" + euaRequestBody);
		Provider provider = new Provider();

		com.dhp.sdk.beans.Provider providerDto;

		provider.setName(req.getMessage().getIntent().getProvider().getDescriptor().getName());
		Example<Provider> providerExample = Example.of(provider);
		List<Provider> result = _providerRepo.findAll(providerExample);
		// System.out.println("_____________"+result.get(0));

		if (!result.isEmpty()) {
			providerDto = modelMapper.map(result.get(0), com.dhp.sdk.beans.Provider.class);

			providerDto = extractedProviderDescriptor(providerDto, result);
			System.out.println("_____________" + providerDto);
			int counterForPerson = 0;

			providerDto = extractedCategoryDescriptorNames(providerDto, result);
			providerDto = extractedPerson(providerDto, result, counterForPerson);

			List<Fulfillments> fulfillmentInResult = result.get(0).getFulfillments();
			// List<Fulfillment> fulfillmentInResult =

			int counterForTimer = 0;

			// providerDto = extractedTimer(providerDto, fulfillmentInResult,
			// counterForTimer);

			ArrayList<com.dhp.sdk.beans.Provider> providerList = new ArrayList<>();
			providerList.add(providerDto);

			euaRequestBody = extractedContext(euaRequestBody, providerList);
		}
		return euaRequestBody;
	}

	// My code

	private OnTBody getProviderById(HspRequestBody req) {
		com.dhp.sdk.beans.Provider providerDto;
		OnTBody requestBody = new OnTBody();
		requestBody =extractedSelectContext(req, requestBody);
		Provider provider = new Provider();
		provider.setProviderId(Integer.parseInt(req.getMessage().getOrder().getProvider().getProviderId()));
		Example<Provider> providerExample = Example.of(provider);
		List<Provider> provider2 = _providerRepo.findAll(providerExample);
		providerDto = modelMapper.map(provider2.get(0), com.dhp.sdk.beans.Provider.class);

		System.out.println(providerDto );
		Integer fulfillmentId = Integer.parseInt(req.getMessage().getOrder().getItems().get(0).getFulfillment_id());
		//System.out.println("__________"+ providerId+"  "+fulfillmentId);
		//List<Fulfillments> findByFulfillmentIdAndProvider = fulfillmentsRepo.findByFulfillmentIdAndProvider(fulfillmentId,provider);
		//System.out.println("Data:"+findByFulfillmentIdAndProvider.toString());
		/*
		 * 
		 * 
		 * com.dhp.sdk.beans.Provider providerDto;
		 * 
		 * provider.setName(req.getMessage().getIntent().getProvider().getDescriptor().
		 * getName()); Example<Provider> providerExample = Example.of(provider);
		 * List<Provider> result = _providerRepo.findAll(providerExample); //
		 * System.out.println("_____________"+result.get(0));
		 * 
		 * if(!result.isEmpty()){ providerDto = modelMapper.map(result.get(0),
		 * com.dhp.sdk.beans.Provider.class);
		 * 
		 * providerDto = extractedProviderDescriptor(providerDto, result);
		 * System.out.println("_____________"+providerDto); int counterForPerson = 0;
		 * 
		 * providerDto = extractedCategoryDescriptorNames(providerDto, result);
		 * providerDto = extractedPerson(providerDto, result, counterForPerson);
		 * 
		 * List<Fulfillments> fulfillmentInResult = result.get(0).getFulfillments(); //
		 * List<Fulfillment> fulfillmentInResult =
		 * 
		 * int counterForTimer =0;
		 * 
		 * //providerDto = extractedTimer(providerDto, fulfillmentInResult,
		 * counterForTimer);
		 * 
		 * ArrayList<com.dhp.sdk.beans.Provider> providerList = new ArrayList<>();
		 * providerList.add(providerDto);
		 * 
		 * euaRequestBody = extractedContext(euaRequestBody, providerList); }
		 */
		return requestBody;
	}

	//

	private com.dhp.sdk.beans.Provider extractedProviderDescriptor(com.dhp.sdk.beans.Provider providerDto,
			List<Provider> result) {
		Descriptor providerDescriptor = new Descriptor();
		providerDescriptor.setName(result.get(0).getName());
		providerDto.setDescriptor(providerDescriptor);

		return providerDto;
	}

	private com.dhp.sdk.beans.Provider extractedCategoryDescriptorNames(com.dhp.sdk.beans.Provider providerDto,
			List<Provider> result) {
		int categoryDescriptorCount = 0;
		for (Categories categories : result.get(0).getCategories()) {
			Descriptor categoryDescriptor = new Descriptor();
			categoryDescriptor.setName(categories.getName());
			providerDto.getCategories().get(categoryDescriptorCount).setDescriptor(categoryDescriptor);
			categoryDescriptorCount++;
		}
		return providerDto;
	}

	private EuaRequestBody extractedContext(EuaRequestBody euaRequestBody,
			ArrayList<com.dhp.sdk.beans.Provider> providerList) {
		Message message = new Message();
		Catalog catalog = new Catalog();
		Descriptor catlogDescriptor = new Descriptor();
		catlogDescriptor.setName("Practo");
		catalog.setDescriptor(catlogDescriptor);
		catalog.setProviders(providerList);

		message.setCatalog(catalog);
		euaRequestBody.setMessage(message);
		return euaRequestBody;
	}

	private com.dhp.sdk.beans.Provider extractedTimer(com.dhp.sdk.beans.Provider providerDto,
			List<Fulfillments> fulfillmentInResult, int counterForTimer) {
		for (Fulfillments fulfillments : fulfillmentInResult) {
			Start start = new Start();
			Time time = new Time();
			time.setTimestamp(fulfillments.getStartTime());
			start.setTime(time);

			End end = new End();
			time = null;
			time = new Time();
			time.setTimestamp(fulfillments.getEndTime());
			end.setTime(time);

			providerDto.getFulfillments().get(counterForTimer).setStart(start);
			providerDto.getFulfillments().get(counterForTimer).setEnd(end);
			counterForTimer++;
		}

		return providerDto;
	}

	private com.dhp.sdk.beans.Provider extractedPerson(com.dhp.sdk.beans.Provider providerDto, List<Provider> result,
			int counterForPerson) {
		for (Fulfillments fulfillments : result.get(0).getFulfillments()) {
			Person personDto = new Person();
			personDto.setPersonId(String.valueOf(fulfillments.getPractitionerId().getPractitionerId()));
			personDto.setName(fulfillments.getPractitionerId().getName());
			personDto.setCred(fulfillments.getPractitionerId().getCred());
			if (fulfillments.getPractitionerId().getGender() == 'M') {
				personDto.setGender("Male");
			} else if (fulfillments.getPractitionerId().getGender() == 'F') {
				personDto.setGender("Female");
			} else {
				personDto.setGender("Others");
			}
			personDto.setImage(fulfillments.getPractitionerId().getImage());

			providerDto.getFulfillments().get(counterForPerson).setPerson(personDto);
			counterForPerson++;

		}

		return providerDto;
	}

	private EuaRequestBody extractedContext(HspRequestBody req, EuaRequestBody euaRequestBody) {
		// String messageId = req.getContext().getMessage_id();
		euaRequestBody.setContext(req.getContext());
		euaRequestBody.getContext().setAction("on_search");
		// euaRequestBody.getContext().setMessage_id(messageId);

		return euaRequestBody;
	}

	private OnTBody extractedSelectContext(HspRequestBody req, OnTBody selectBody) {
		selectBody.setContext(req.getContext());
		selectBody.getContext().setAction("on_select");
		return selectBody;
	}

}
