/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.interestcalculatorjspservlet;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author James
 */
public interface Service {
    
    public BigDecimal calcEndPrincipal(BigDecimal begPrincipal, BigDecimal quarterlyRate);
    
    public BigDecimal calcInterestEarned(BigDecimal begPrincipal, BigDecimal endPrincipal);
    
    public DTO fillDTO(DTO dto, BigDecimal begPrincipal, BigDecimal quarterlyRate);
    
    public DTO addDTO(DTO filledDTO);
    
    public List<DTO> getAllDTO();
 
}
