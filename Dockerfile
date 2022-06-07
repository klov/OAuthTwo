ARG DOCKER_REGISTRY="docker.io"
FROM ${DOCKER_REGISTRY}/python:3.8-buster
LABEL maintainer="vodenisov@dev.vtb"

ARG PIP_INDEX_URL
ARG PIP_TRUSTED_HOST
ARG APP_HOME=/opt/rpc_module

WORKDIR $APP_HOME
COPY . $APP_HOME
RUN chgrp -R 0 $APP_HOME && chmod -R g+rwX $APP_HOME && pip install -r requirements.txt
ENTRYPOINT ["./docker-entrypoint.sh"]
CMD ["rpc_module"]
