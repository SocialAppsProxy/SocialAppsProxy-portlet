create table OAuthProxy_OAuthConnection (
	connectionId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	token VARCHAR(75) null,
	tokenSecret VARCHAR(75) null,
	realm VARCHAR(75) null,
	phase INTEGER
);

create table OAuthProxy_OAuthProvider (
	providerId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	consumerKey VARCHAR(75) null,
	consumerSecret VARCHAR(75) null,
	realm VARCHAR(75) null,
	requestTokenURL VARCHAR(75) null,
	authoriseURL VARCHAR(75) null,
	accessTokenURL VARCHAR(75) null,
	protocolVersion VARCHAR(75) null,
	preemptiveAuthURLPattern VARCHAR(75) null,
	useHTTPS BOOLEAN
);