package com.spider.granjateu.services;

import com.spider.granjateu.entities.LoteAves;
import com.spider.granjateu.repositories.LoteAvesRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class LoteAvesServiceTest {

	@InjectMocks
	private LoteAvesService loteAvesService;

	@Mock
	private LoteAvesRepository loteAvesRepository;


}
