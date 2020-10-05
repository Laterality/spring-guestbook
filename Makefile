.PHONY: build-api

build-api:
	./gradlew guestbook-api:build
	docker build -t laterality/guestbook-api:latest guestbook-api

publish-api:
	docker login
	docker push laterality/guestbook-api:latest