package org.kpagan.clash.clashserver.web;

import java.util.Optional;

public interface QueryHandler {

	QueryResponse handle(Optional<String> query);

}
