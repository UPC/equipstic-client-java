#!/bin/bash

# Modifiqueu les variables amb els valors oportuns
export SOA_URL="https://bus-soades.upc.edu/EquipsTIC/APIv1"
export SOA_USERNAME="my_soa_username"
export SOA_PASSWORD="my_soa_password"

mvn test
