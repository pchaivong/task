## Simple Task for Kubernetes

### Just a simple springboot application to run on kubernetes cluster

#### Steps:

- replace `src/main/resource/creds.json` with your ibp connection profile
- Compile to jar `mvn build install`
- Build docker image `docker build -t registry.au-syd.bluemix.net/<namespace>/<image>:<version>`
- Push to repository `docker push  registry.au-syd.bluemix.net/<namespace>/<image>:<version>`
- Edit kube/task.yaml to use your image
- Create task in kubernetes cluster `kubectl create -f ./kube/task.yaml`