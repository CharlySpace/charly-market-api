package com.charly.market.auth.service.provider;

import com.charly.market.auth.model.dto.OAuthUserInfo;

public interface OAuth2Provider {
  OAuthUserInfo getUserInfo(String code);
}

