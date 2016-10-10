#!/bin/bash

# Modifiqueu les variables amb els valors oportuns
export EQUIPSTIC_SOA_URL="https://bus-soades.upc.edu/EquipsTIC/APIv1"
export EQUIPSTIC_SOA_USERNAME="my_soa_username"
export EQUIPSTIC_SOA_PASSWORD="my_soa_password"

mvn test
