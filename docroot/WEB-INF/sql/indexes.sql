create index IX_6397D86F on OAuthProxy_OAuthConnection (companyId, groupId, userId);
create index IX_C57EA630 on OAuthProxy_OAuthConnection (companyId, groupId, userId, realm);
create index IX_E7FE9AEC on OAuthProxy_OAuthConnection (realm);
create index IX_3B27F46D on OAuthProxy_OAuthConnection (userId);
create index IX_1D14B072 on OAuthProxy_OAuthConnection (userId, realm);

create index IX_A78E481D on OAuthProxy_OAuthProvider (companyId, realm);
create index IX_F9826979 on OAuthProxy_OAuthProvider (realm);