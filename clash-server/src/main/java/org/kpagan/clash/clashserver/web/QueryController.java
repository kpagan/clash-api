package org.kpagan.clash.clashserver.web;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kpagan
 *
 */
@RestController
@RequestMapping(QueryController.COMMAND_PATH)
public class QueryController {

	public static final String COMMAND_PATH = "/api";

	@Autowired
	private Map<String, QueryHandler> queryHandlers;

	@RequestMapping(value = { "/{queryId}/{query}" }, method = RequestMethod.GET)
	public ResponseEntity<QueryResponse> handleQuery(@PathVariable("queryId") String queryId,
			@PathVariable("query") Optional<String> query, @RequestParam Map<String, String> params) {
		QueryResponse QueryResponse = queryHandlers.get(queryId).handle(query, params);
		return new ResponseEntity<QueryResponse>(QueryResponse, HttpStatus.OK);
	}

}
