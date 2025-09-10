package com.charly.market.global.security;

import java.util.List;

public record UserPrincipal (Long userId, String email, List<String> roles) {

}