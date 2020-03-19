package com.bitbox.dao;

import com.bitbox.model.Item;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Jose Manuel Milla Darias
 * @version 1.0 Date: 14/03/2020
 */
public interface DAOItem extends CrudRepository<Item, Long> {}