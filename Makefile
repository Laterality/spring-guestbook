.PHONY: build-api

build-api:
	./gradlew guestbook-api:build
	docker build -t guestbook-api:latest guestbook-api
