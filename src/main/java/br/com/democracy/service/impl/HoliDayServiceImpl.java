package br.com.democracy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.democracy.dao.HoliDaysDAO;
import br.com.democracy.dto.HoliDayDTO;
import br.com.democracy.exception.ServiceException;
import br.com.democracy.exception.ValidationException;
import br.com.democracy.helper.ConvertHelper;
import br.com.democracy.persistence.HoliDays;
import br.com.democracy.service.HoliDayService;

@Service
public class HoliDayServiceImpl implements HoliDayService {

	@Autowired
	private HoliDaysDAO holiDaysDAO;

	@Override
	@Transactional(rollbackFor = ServiceException.class)
	public void newHoliday(HoliDayDTO holiDayDTO) throws ValidationException, ServiceException {

		// cria novo objeto
		HoliDays holiDay = new HoliDays();
		holiDay.setDescription(holiDayDTO.getDescription());
		holiDay.setDate(ConvertHelper.parseDateShort(holiDayDTO.getDate()));

		// salva holiday no banco
		holiDaysDAO.saveOrUpdate(holiDay);

		// valida se já existe um feriado com a mesma descricao ou data
		List<HoliDays> allHoliDays = holiDaysDAO.getAll();
		int count = 0;
		for (HoliDays hol : allHoliDays) {
			if (hol.getDescription().equals(holiDay.getDescription())
					|| hol.getDate().compareTo(holiDay.getDate()) == 0) {
				count++;
			}

			if (count > 1) {
				throw new ServiceException(
						"Já existe um feriado com esta descrição ou data");
			}
		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<HoliDayDTO> listHoliDays() {
		// pega lista no banco
		List<Object[]> holiDays = holiDaysDAO.listHoliDays();

		// copia lista para o DTO
		List<HoliDayDTO> returnList = new ArrayList<HoliDayDTO>();
		for (Object[] holiDay : holiDays) {
			returnList.add(HoliDayDTO.copy(holiDay));
		}

		// retorna lista
		return returnList;
	}

}
