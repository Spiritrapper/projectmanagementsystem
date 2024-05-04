- start springboot


- Spring Security 구성

    - 상태 관리: 서버에서는 상태를 관리하는 정책을 제공해야 합니다. 무상태 생성 정책을 사용한다는 것을 언급하며, 이는 HTTP 요청을 승인하고 엔드포인트 보안을 정의하는데 사용됩니다.
      세션 생성 정책: 세션 생성 정책을 언급하며, 로그인한 사용자만 접근할 수 있다고 설명됩니다.
      엔드포인트 보안: 어떤 엔드포인트를 사용해야 하는지와 같은 엔드포인트 보안을 정의하는 것이 중요하며, 로그인 또는 등록 없이 접근 가능한 엔드포인트와 로그인한 사용자만 접근 가능한 엔드포인트를 구분합니다.
      JWT 토큰: JWT 토큰을 사용하여 인증을 수행하며, 인증된 엔드포인트에서는 JWT 토큰의 존재 여부를 확인합니다.
      CSRF 보안: CSRF 공격을 방지하기 위해 CSRF 보안을 제공해야 하며, 여기서는 CSRF를 비활성화하는 것으로 언급됩니다.
      필터 클래스: JWT 토큰의 유효성을 검사하는 필터 클래스를 제공해야 합니다.
  
    - 코스 구성 생성 및 설정:
       코스 구성을 의미하는 코스 구성 CFG를 새로 만듭니다.
      구성 인스턴스를 설정합니다. Origins 설정:
        Origins 설정을 위해 프런트엔드 URL을 제공해야 합니다.
        프런트엔드 URL을 배열로 설정하여 백엔드에 액세스할 수 있도록 합니다.
      다양한 URL 설정:
        여러 프런트엔드 URL을 제공하고, 이를 통해 백엔드에 액세스할 수 있도록 합니다.
      CFG 설정:
        CFG를 사용하여 요청 방법 및 허용된 메소드를 설정합니다.
        헤더 설정을 추가하고, 허용된 헤더를 지정합니다.
        다른 설정도 추가할 수 있으며, 예를 들어 게시 방법에 대한 권한을 설정할 수 있습니다.
        정리:
        코스 구성이 완료되었으며, 설정이 정상적으로 적용되었습니다 

    - 이 코드는 JWT(Jason Web Token)를 사용하여 사용자를 인증하는 필터의 일부분입니다. 각 줄을 해석해보겠습니다.
    if(jwt!=null){: JWT가 null이 아닌 경우를 확인하는 조건문입니다. JWT가 요청에서 제공되었는지 확인합니다.
    jwt=jwt.substring(7);: JWT에서 "Bearer " 부분을 제거하여 실제 JWT 토큰만을 추출합니다. "Bearer "는 일반적으로 JWT 토큰 앞에 포함되는 인증 스킴입니다.
    SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECREATE_KEY.getBytes());: HMAC-SHA 알고리즘을 사용하여 JWT를 서명하는 비밀 키를 생성합니다. JwtConstant.SECREATE_KEY는 이 비밀 키의 값을 제공합니다.
    Claims claims = Jwts.parser().build().parseSignedClaims(jwt).getBody();: JWT를 파싱하여 페이로드를 추출합니다. 이 페이로드에는 사용자 정보와 권한 등이 포함될 수 있습니다.
    String email=String.valueOf(claims.get("email"));: JWT 페이로드에서 사용자 이메일을 추출합니다.
    String authorities=String.valueOf(claims.get("authorities"));: JWT 페이로드에서 사용자의 권한 정보를 추출합니다.
    List<GrantedAuthority> auths= AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);: 사용자의 권한 정보를 Spring Security의 GrantedAuthority 객체로 변환합니다.
    Authentication authentication=new UsernamePasswordAuthenticationToken(email,null,auths);: 인증 객체를 생성합니다. 사용자의 이메일과 권한 정보가 포함됩니다.
    SecurityContextHolder.getContext().setAuthentication(authentication);: Spring Security의 SecurityContextHolder를 사용하여 현재 사용자의 인증 정보를 설정합니다.
    filterChain.doFilter(request, response);: 다음 필터로 요청을 전달합니다. 이 코드가 없으면 요청이 더 이상 처리되지 않고 중단됩니다.

- modal 패키지 구성
  - 

- repository 패키지 구성

- service 패키지

- controller 패키지

- response 패키지